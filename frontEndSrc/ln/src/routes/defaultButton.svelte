<script lang="ts">
    import "../app.css";
    let { onClickHandler, children, isWhite } = $props();
</script>

<button
    onclick={onClickHandler != null ? onClickHandler : () => {}}
    class="{isWhite != 'true'
        ? 'bg-blue-500'
        : 'bg-white'} hover:bg-blue-700 {isWhite != 'true'
        ? 'text-white'
        : 'text-blue-500'} font-bold py-2 px-4 rounded flex flex-row cursor-pointer"
>
    {@render children()}
</button>
