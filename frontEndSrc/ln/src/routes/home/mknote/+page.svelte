<script lang="ts">
    import { Carta, MarkdownEditor } from "carta-md";
    import "$lib/styles/github.scss";
    import "../../../app.css";
    import ShareNotesBtn from "../shareNotesBtn.svelte";
    import DefaultButton from "../../defaultButton.svelte";
    import { json } from "@sveltejs/kit";
    const carta = new Carta({
        sanitizer: false,
    });
    let val = $state("");
    function checkifSmth(x: any) {
        return x == "" || x == undefined || x == null;
    }
    async function upload() {
        if (checkifSmth(val)) {
            alert("no value on the note");
            return;
        }
        if (checkifSmth(title)) {
            alert("No title");
            return;
        }
        if (checkifSmth(tags)) {
            alert("No tags");
            return;
        }
        const response = await fetch("http://localhost:8080/note", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                title: title,
                contenido: val,
                user_id: getUser().id,
                tags: tags.split(" "),
            }),
        });
        if (response.status == 201) {
            alert("Created");
            location.href = "/home";
        }
    }
    function verifyUser(): null | undefined {
        if (localStorage.getItem("user") === null) return null;
    }
    function getUser(): object {
        if (verifyUser() === null) return {};
        return JSON.parse(localStorage.getItem("user") as string);
    }
    let title = $state("");
    let tags = $state("");
</script>

{#if typeof window !== "undefined"}
    {#if verifyUser() !== null}
        <div class="w-full min-h-20 bg-blue-500 flex items-center flex-col">
            <ShareNotesBtn></ShareNotesBtn>
        </div>
        <br />
        <div class="w-screen flex items-center flex-col">
            <input
                type="text"
                placeholder="Titulo"
                bind:value={title}
                class="bg-gray-200 rounded text-center mr-2 w-1/3"
            />
            <br />
            <input
                type="text"
                bind:value={tags}
                placeholder="#Tags"
                class="bg-gray-200 rounded text-center mr-2 w-1/3"
            />
            <br />
            <DefaultButton onClickHandler={upload}>Subir!</DefaultButton>
        </div>
        <div class="w-screen p-10 carta-theme">
            <MarkdownEditor
                bind:value={val}
                {carta}
                mode="tabs"
                theme="github"
            />
        </div>
    {/if}
    <!-- content here -->
{/if}

<style lang="scss">
    @import "../../theme.css";
</style>
