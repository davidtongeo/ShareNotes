<script lang="ts">
    import { ssrExportAllKey } from "vite/module-runner";
    import Note from "./note.svelte";

    let { asyncFunction, searchInput } = $props();
    function matchCase(
        obj: string[] | null | undefined,
        input: string[] | null | undefined,
    ): boolean {
        return obj.some(
            (el) =>
                typeof el === "string" &&
                input.some(
                    (eld) =>
                        typeof eld === "string" &&
                        el
                            .toLocaleLowerCase()
                            .startsWith(eld.toLocaleLowerCase()),
                ),
        );
    }
</script>

{#await asyncFunction()}
    <p>Loading...</p>
{:then data: any}
    {#if searchInput != "" || searchInput != null}
        {#if !searchInput.startsWith("#")}
            {#each data as jsonComponent}
                {#if jsonComponent.title
                    .toLocaleLowerCase()
                    .startsWith(searchInput.toLocaleLowerCase())}
                    <Note pObject={jsonComponent}></Note>
                {/if}
            {/each}
        {:else}
            {#each data as jsonComponent}
                {#if matchCase(jsonComponent.tags, searchInput.split())}
                    <Note pObject={jsonComponent}></Note>
                {/if}
            {/each}
        {/if}
    {/if}
{/await}
