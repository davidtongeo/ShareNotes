<script lang="ts">
    import "../../../../app.css";
    import ShareNotesBtn from "../../shareNotesBtn.svelte";
    import { fade, scale } from "svelte/transition";
    import brain from "$lib/assets/brain.png";
    import shareNotes from "$lib/assets/sn.png";
    import Note from "../../note.svelte";
    import DefaultButton from "../../../defaultButton.svelte";
    import search from "$lib/assets/search.svg";
    import ProfileContainer from "../../profileContainer.svelte";
    let pf = $state("");
    async function getinfo() {
        const response = await fetch("http://localhost:8080/notes", {
            method: "GET",
        });
        if (response.status != 302) {
            console.error("failed to fetch");
            return;
        }
        const json = await response.json();
        return [...json].reverse();
    }
    async function getUser(id: number) {
        const response = await fetch(`http://localhost:8080/profile/${id}`, {
            method: "GET",
        });
        if (response.status != 302) {
            console.error("failed to fetch");
            return;
        }
        return await response.json();
    }
    import { page } from "$app/state";
    let { id } = page.params;
</script>

<div class="w-full h-full flex items-center flex-col">
    <div class="w-full h-20 bg-blue-500 flex justify-center">
        <!-- <img class="w-30 m-2" src={shareNotes} alt="" /> -->
        <ShareNotesBtn></ShareNotesBtn>
    </div>
    <br />
    <img src={brain} alt="" class="rounded-full w-1/9 border-3 mb-2" />
    <h1 class="text-center">
        Perfil de
        {#await getUser(parseInt(id))}
            ... loading
        {:then data}
            <p1 class="font-bold">{data.username}</p1>
        {/await}
    </h1>
    <div class="h-full w-1/2 bg-gray-200 flex flex-col items-center m-5 p-5">
        {#await getinfo()}
            <p>Loading...</p>
        {:then data}
            {#each data as jsonComponent: any}
                {#if jsonComponent.user.id == id}
                    <Note pObject={jsonComponent}></Note>
                {/if}
            {/each}
        {/await}
    </div>
</div>

<style>
    .no-scrollbar::-webkit-scrollbar {
        display: none;
    }
</style>
