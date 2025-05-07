<script lang="ts">
    import { ssrExportAllKey } from "vite/module-runner";
    import { Fzf } from "fzf";
    import Note from "./note.svelte";
    import { scale } from "svelte/transition";
    let { asyncFunction, searchInput } = $props();
    function verifyUser(): null | undefined {
        if (localStorage.getItem("user") === null) return null;
    }
    function getUser(): object {
        if (verifyUser() === null) return {};
        return JSON.parse(localStorage.getItem("user") as string);
    }
    function matchCase(inputText: string, iterable: any[], type: string) {
        switch (type.toLocaleLowerCase()) {
            case "tag": {
                const fzf = new Fzf(iterable, {
                    selector: (item) => (item.tags ?? []).join(" "),
                });
                return fzf.find(inputText);
            }
            case "user": {
                const fzf = new Fzf(iterable, {
                    selector: (item) => item.user.username,
                });
                return fzf.find(inputText);
            }
            default: {
                const fzf = new Fzf(iterable, {
                    selector: (item) => item.title,
                });
                return fzf.find(inputText);
            }
        }
    }
</script>

{#await asyncFunction()}
    <p>Loading...</p>
{:then data: any}
    {#if searchInput != ""}
        {#if !searchInput.startsWith("#")}
            {#each matchCase(searchInput.toLocaleLowerCase(), data, "default") as jsonComponent}
                {console.log(jsonComponent)}
                {#if jsonComponent !== null}
                    <Note pObject={jsonComponent.item}></Note>
                {:else}{/if}
            {/each}
        {/if}
        {#if searchInput.startsWith("@")}
            {console.log("user")}
            {#each matchCase(searchInput
                    .toLocaleLowerCase()
                    .substring(1, searchInput.length), data, "user") as jsonComponent}
                <Note isAdmin={getUser().admin} pObject={jsonComponent.item}
                ></Note>
            {/each}
        {:else}
            {#each matchCase(searchInput
                    .toLocaleLowerCase()
                    .substring(1, searchInput.length), data, "tag") as jsonComponent}
                <Note isAdmin={getUser().admin} pObject={jsonComponent.item}
                ></Note>
            {/each}
        {/if}
    {:else}
        {#each data as jsonComponent}
            <Note isAdmin={getUser().admin} pObject={jsonComponent}></Note>
        {/each}
    {/if}
{/await}
