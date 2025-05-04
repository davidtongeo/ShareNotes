<script lang="ts">
    import "../../app.css";
    import brain from "$lib/assets/brain.png";
    import DefaultButton from "../defaultButton.svelte";
    let { name } = $props();
</script>

<div
    class="bg-blue-400 w-1/2 flex justify-center items-center flex-col p-3 rounded"
>
    <img src={brain} alt="" class="rounded-full w-1/5 border-3 mb-2" />
    <h1 class="text-white font-bold">Hola! {name}</h1>
    <DefaultButton isWhite="true" onClickHandler={null}
        >Crear Apunte</DefaultButton
    >
</div>
