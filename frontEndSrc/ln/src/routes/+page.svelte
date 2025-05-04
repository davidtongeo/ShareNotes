<script lang="ts">
    import Inputs from "./inputs.svelte";
    import shareNotes from "$lib/assets/sn.png";
    import fondo from "$lib/assets/fondolog.jpg";
    import DefaultButton from "./defaultButton.svelte";
    import "../app.css";
</script>

<div
    class="w-screen h-screen flex justify-center items-center bg-cover bg-no-repeat bg-center fixed"
    style={`background-image: url('${fondo}')`}
>
    <div
        class="bg-white/30 min-h-4/5 min-w-1/3 flex items-center rounded flex-col backdrop-blur-mg p-8"
    >
        <img src={shareNotes} class="filter invert h-20 pt-2" alt="" />
        <p class="font-bold pt-10">
            Una app que te permite tomar y compartir notas.
        </p>
        <a
            href="https://google.com"
            class="w-full font-bold text-center cursor-pointer text-sm text-blue-600 hover:underline"
            >¿Como funciona?</a
        >
        <div class=" h-full w-full flex flex-row justify-center items-center">
            <Inputs></Inputs>
        </div>
    </div>
</div>
