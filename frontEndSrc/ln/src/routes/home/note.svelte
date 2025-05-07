<script lang="ts">
    let props = $props();
    import NoteFs from "./noteFS.svelte";
    import Tag from "./tag.svelte";
    import "../../app.css";
    import { Carta } from "carta-md";
    let carta = new Carta({ sanitizer: false, theme: "github-light" });
    import heartIcon from "$lib/assets/heartIcon.svg";
    import DefaultButton from "../defaultButton.svelte";
    import { scale } from "svelte/transition";
    let maxChar = props.maxChar != null ? props.maxChar : 200;
    let showFS = $state(false);
    function funHandler() {
        showFS = !showFS;
    }
    function verifyUser(): null | undefined {
        if (localStorage.getItem("user") === null) return null;
    }
    function getUser(): object {
        if (verifyUser() === null) return {};
        return JSON.parse(localStorage.getItem("user") as string);
    }

    function getContent() {
        return props.pObject.content.toString().length > maxChar
            ? props.pObject.content.toString().substring(0, maxChar) + "..."
            : props.pObject.content;
    }
</script>

{#if showFS}
    <NoteFs pObject={props.pObject} fun={funHandler}></NoteFs>
{/if}
<!-- svelte-ignore a11y_click_events_have_key_events -->
<!-- svelte-ignore a11y_no_static_element_interactions -->
<div
    transition:scale
    class="bg-gray-100 w-3/4 m-10 rounded hover:bg-blue-200 transition-colors ease-in-out duration-200 p-2 cursor-pointer"
    onclick={funHandler}
>
    <h1 class="font-bold">{props.pObject.title}&nbsp;</h1>
    <a href="#!" class="text-blue-700 cursor-pointer"
        >Usuario: {props.pObject.user.username}</a
    >
    <div class="bg-gray-200 w-full rounded">
        {#await carta.render(getContent()) then content}
            <div class=" max-h-2/3 wrap-break-word carta-theme p-2">
                {@html content}
            </div>
        {/await}
    </div>
    <h1 class="mt-3 mb-3 font-bold">Tags</h1>
    <div class="flex flex-row w-full">
        {#each props.pObject.tags as tag}
            <Tag>{tag}</Tag>
        {/each}
    </div>
    <br />
    <DefaultButton isWhite={null} onClickHandler={null}>
        <img src={heartIcon} class="w-5 h-5 mr-4" alt="" />
        {props.pObject.like}
    </DefaultButton>
    {console.log(props.pObject)}
    {#if props.isAdmin || getUser().id == props.pObject.user.id}
        <br />
        <DefaultButton isWhite={null} onClickHandler={null}
            >Eliminar</DefaultButton
        >
    {/if}
</div>

<style lang="scss">
    @import "../theme.css";
</style>
