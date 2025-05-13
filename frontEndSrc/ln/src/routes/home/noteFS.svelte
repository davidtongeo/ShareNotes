<script lang="ts">
    import "../../app.css";
    import Tag from "./tag.svelte";
    import { Carta } from "carta-md";
    let carta = new Carta({ sanitizer: false, theme: "github-light" });
    import DefaultButton from "../defaultButton.svelte";
    import { fade, scale } from "svelte/transition";
    import heartIcon from "$lib/assets/heartIcon.svg";
    import { goto } from "$app/navigation";
    import { json } from "@sveltejs/kit";
    let props = $props();
    let maxChar = props.maxChar != null ? props.maxChar : 200;
    function verifyUser(): null | undefined {
        if (localStorage.getItem("user") === null) return null;
    }
    function getUser(): object {
        if (verifyUser() === null) return {};
        return JSON.parse(localStorage.getItem("user") as string);
    }
    let content = $state("");
    async function del() {
        const resp = await fetch(
            `http://localhost:8080/notes/${props.pObject.id}`,
            { method: "DELETE" },
        );
        if (resp.status == 204) {
            alert("Note deleted");
            location.reload();
        }
    }
    async function comment() {
        const resp = await fetch(`http://localhost:8080/comment`, {
            headers: { "Content-Type": "application/json" },
            method: "POST",
            body: JSON.stringify({
                userId: getUser().id,
                noteId: props.pObject.id,
                content: content,
            }),
        });
        if (resp.status == 201) {
            alert("Created");
            props.refetch();
        }
    }
    async function like() {
        const resp = await fetch("http://localhost:8080/like", {
            headers: { "Content-Type": "application/json" },
            method: "POST",
            body: JSON.stringify({
                idUser: getUser().id,
                idNote: props.pObject.id,
            }),
        });
        if (resp.status == 200) {
            alert("Done");
            props.refetch();
        }
    }
</script>

<div class="w-full h-full z-20 flex justify-center items-center fixed top-0">
    <!-- svelte-ignore a11y_no_static_element_interactions -->
    <!-- svelte-ignore a11y_click_events_have_key_events -->
    <div
        class="w-full h-full absolute z-20 m-0 top-0 flex justify-center items-center"
        transition:fade
        style="webkit-scrollbar: 0; background-color: rgba(0, 0, 0, 0.5);"
        onclick={props.fun}
    ></div>
    <!-- svelte-ignore a11y_no_static_element_interactions -->
    <!-- svelte-ignore a11y_click_events_have_key_events -->
    <div
        class="bg-white w-2/3 h-2/3 opacity-100 rounded p-10 z-30 overflow-scroll no-scrollbar"
        onclick={null}
        transition:scale
    >
        <h1 class="font-bold">{props.pObject.title}&nbsp;</h1>
        <a
            href={"/home/pf/" + props.pObject.user.id}
            class="text-blue-700 cursor-pointer"
            >Usuario: {props.pObject.user.username}</a
        >
        <h1 class="mt-3 mb-3 font-bold">Tags</h1>
        <div class="flex flex-row w-full mt-2">
            {#each props.pObject.tags as tag}
                <Tag>{tag}</Tag>
            {/each}
        </div>
        {#if getUser().admin || getUser().id == props.pObject.user.id}
            <br />
            <DefaultButton isWhite={null} onClickHandler={del}
                >Eliminar</DefaultButton
            >
            <br />
        {/if}
        <div class="bg-gray-200 w-full rounded">
            {#await carta.render(props.pObject.content) then content}
                <div class=" max-h-2/3 wrap-break-word carta-theme m-2">
                    {@html content}
                </div>
            {/await}
        </div>
        <br />
        <DefaultButton isWhite={null} onClickHandler={like}>
            <img src={heartIcon} class="w-5 h-5 mr-4" alt="" />
            {props.pObject.like}
        </DefaultButton>
        <br />
        <h1>Comenta!</h1>
        <div>
            <input
                bind:value={content}
                type="text"
                placeholder="Hola"
                class="border-blue-100 border-3 rounded mb-2 w-full"
            />
            <DefaultButton isWhite={"false"} onClickHandler={comment}
                >publicar</DefaultButton
            >
            {console.log(props.pObject.comments)}
            <br />
            {#each [...props.pObject.comments].reverse() as item}
                <div class="w-full bg-gray-100 p-2">
                    <a href={`/home/pf/${item.user.id}`} class="text-blue-500"
                        >{item.user.username}</a
                    >
                    <p class="whitespace-pre-wrap break-words">
                        {item.content}
                    </p>
                </div>
                <br />
            {/each}
        </div>
    </div>
</div>

<style lang="scss">
    @import "../theme.css";
    .no-scrollbar::-webkit-scrollbar {
        display: none;
    }
</style>
