<script lang="ts">
    import "../app.css";
    let { onClickHandler, children, isWhite } = $props();
</script>

<button
    onclick={onClickHandler != null ? onClickHandler : () => {}}
    class="{isWhite != 'true'
        ? 'bg-blue-500 text-white'
        : 'bg-white text-black'} transition-all duration-200 hover:invert filter font-bold py-2 px-4 rounded flex flex-row cursor-pointer"
>
    {@render children()}
</button>
