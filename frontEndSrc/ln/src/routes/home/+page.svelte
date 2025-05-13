<script lang="ts">
    import { onMount } from "svelte";
    import ShareNotesBtn from "./shareNotesBtn.svelte";
    import ProfileContainer from "./profileContainer.svelte";
    import Note from "./note.svelte";
    import DefaultButton from "../defaultButton.svelte";
    import shareNotes from "$lib/assets/sn.png";
    import search from "$lib/assets/search.svg";
    import LoadNote from "./loadNote.svelte";
    import { scale } from "svelte/transition";
    let reloadKey = $state(0);

    function reload() {
        reloadKey = reloadKey + 1;
    }
    async function getAllPost() {
        const response = await fetch("http://localhost:8080/notes", {
            method: "GET",
        });

        if (!(response.status == 302)) {
            console.error("Failed to fetch", response.status);
            return;
        }

        let x = await response.json();
        return [...x].reverse();
    }

    onMount(() => {
        getAllPost();
    });
    let input = $state("");
    function handleInput(event: any) {
        input = event.target.value;
    }
    function verifyUser(): null | undefined {
        if (localStorage.getItem("user") === null) return null;
    }
    function getUser(): object {
        if (verifyUser() === null) return {};
        return JSON.parse(localStorage.getItem("user") as string);
    }
</script>

{#if typeof window !== "undefined"}
    {#if verifyUser() !== null}
        <div class="w-full h-full flex items-center flex-col">
            <div class="w-full min-h-20 bg-blue-500 flex items-center flex-col">
                <ShareNotesBtn></ShareNotesBtn>
            </div>
            <div class="w-1/2 h-10 m-5 flex flex-row">
                <input
                    oninput={handleInput}
                    type="text"
                    placeholder="#tags or text"
                    class="bg-gray-200 w-full h-full rounded text-center mr-2"
                />
            </div>
            <ProfileContainer admin={getUser().admin} name={getUser().username}
            ></ProfileContainer>
            {console.log(getUser())}
            <div
                class="h-full w-1/2 bg-gray-200 flex flex-col items-center m-5 p-5"
            >
                {#key reloadKey}
                    <LoadNote
                        asyncFunction={getAllPost}
                        searchInput={input}
                        update={reload}
                    ></LoadNote>
                {/key}
            </div>
        </div>
    {/if}
{/if}
