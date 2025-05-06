<script lang="ts">
    let loginState: boolean = $state(false);

    import "../app.css";
    import DefaultButton from "./defaultButton.svelte";
    let username = $state("");
    let password = $state("");
    let email = $state("");

    function changeLoginState() {
        loginState = !loginState;
    }
    async function clicklg() {
        const objPass = {
            headers: { "Content-Type": "application/json" },
            method: "POST",
            body: JSON.stringify({ name: username, password: password }),
        };
        const resp = await fetch("http://localhost:8080/loggin", objPass);
        const json = await resp.json();
        console.log(json);
        if (resp.ok && typeof window !== "undefined") {
            localStorage.setItem("user", JSON.stringify(json));
            location.href = "/home";
        } else {
            alert("Couldn't log in.");
        }
    }
    async function clickreg() {
        const resp = await fetch("http://localhost:8080/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                name: username,
                password: password,
                email: email,
            }),
        });
        loginState = !loginState;
        if (resp.ok) {
            alert("Created!");
        } else {
            alert("Couldn't register.");
        }
    }

    const formMinHeight = "390px";
</script>

<form class="w-2/3 h-4/5 flex justify-center items-center flex-col">
    <div
        class="mt-5 p-6 w-full max-w-sm bg-white rounded-lg shadow-md flex flex-col justify-center"
        style="min-height: {formMinHeight};"
    >
        <br />
        {#if !loginState}
            <h2 class="text-xl font-semibold mb-5 text-center text-gray-700">
                Iniciar Sesión
            </h2>

            <label
                for="login-username"
                class="block mb-1 text-sm font-medium text-gray-600"
                >Usuario</label
            >
            <input
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 mb-3"
                type="text"
                id="login-username"
                placeholder="tu_usuario"
                bind:value={username}
            />

            <label
                for="login-password"
                class="block mb-1 text-sm font-medium text-gray-600"
                >Contraseña</label
            >
            <input
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 mb-6"
                type="password"
                id="login-password"
                placeholder="••••••••"
                bind:value={password}
            />

            <a
                href="#!"
                onclick={changeLoginState}
                class="w-full text-center cursor-pointer text-sm text-blue-600 hover:underline"
            >
                ¿No tienes una cuenta? Regístrate
            </a>
        {:else}
            <h2 class="text-xl font-semibold mb-5 text-center text-gray-700">
                Crear Cuenta
            </h2>

            <label
                for="register-username"
                class="block mb-1 text-sm font-medium text-gray-600"
                >Usuario</label
            >
            <input
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 mb-3"
                type="text"
                id="register-username"
                placeholder="elige_un_usuario"
                bind:value={username}
            />

            <label
                for="register-password"
                class="block mb-1 text-sm font-medium text-gray-600"
                >Contraseña</label
            >
            <input
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 mb-3"
                type="password"
                id="register-password"
                placeholder="••••••••"
                bind:value={password}
            />

            <label
                for="email"
                class="block mb-1 text-sm font-medium text-gray-600"
                >Email</label
            >
            <input
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-md focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 mb-6"
                type="email"
                id="email"
                placeholder="tu@correo.com"
                bind:value={email}
            />

            <a
                href="#!"
                onclick={changeLoginState}
                class="w-full text-center cursor-pointer text-sm text-blue-600 hover:underline"
            >
                ¿Ya tienes una cuenta? Inicia sesión
            </a>
        {/if}
        <br />
        <DefaultButton
            isWhite={null}
            onClickHandler={!loginState ? clicklg : clickreg}
            >{!loginState ? "Entrar" : "Registrar"}</DefaultButton
        >
    </div>
</form>
