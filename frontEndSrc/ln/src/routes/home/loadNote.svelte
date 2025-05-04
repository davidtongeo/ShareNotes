<script lang="ts">
    import Note from "./note.svelte";

    let { asyncFunction, searchInput } = $props();
    function matchCase(obj: string[], input: string[]): boolean {
        obj.forEach((el: string) => {
            input.forEach((eld: string) => {
                console.log(eld);
                if (el.toLocaleLowerCase().startsWith(eld)) return true;
            });
        });

        return false; //default
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
            {console.log(!searchInput.startsWith("#"))}
            {#each data as jsonComponent}
                {#if matchCase(jsonComponent.tags, searchInput.split())}
                    <Note pObject={jsonComponent}></Note>
                {/if}
            {/each}
        {/if}
    {/if}
{/await}
