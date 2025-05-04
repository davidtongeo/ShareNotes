<script lang="ts">
    import "../../app.css";
    import { Carta } from "carta-md";
    let carta = new Carta({ sanitizer: false, theme: "github-light" });
    import DefaultButton from "../defaultButton.svelte";
    import { fade, scale } from "svelte/transition";
    import heartIcon from "$lib/assets/heartIcon.svg";
    import { goto } from "$app/navigation";
    let props = $props();
    let maxChar = props.maxChar != null ? props.maxChar : 200;
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
        <div class="bg-gray-200 w-full rounded">
            {#await carta.render(props.pObject.content) then content}
                <div class=" max-h-2/3 wrap-break-word carta-theme m-2">
                    {@html content}
                </div>
            {/await}
        </div>
        <br />
        <DefaultButton isWhite={null} onClickHandler={null}>
            <img src={heartIcon} class="w-5 h-5 mr-4" alt="" />
            {props.pObject.like}
        </DefaultButton>
    </div>
</div>

<style lang="scss">
    @import "../theme.css";
    .no-scrollbar::-webkit-scrollbar {
        display: none;
    }
</style>
