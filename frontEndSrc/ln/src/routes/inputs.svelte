<script lang="ts">
    let loginState: boolean = $state(false);

    import "../app.css";
    import DefaultButton from "./defaultButton.svelte";

    function changeLoginState() {
        loginState = !loginState;
    }
    function clicklg() {
        location.href = "/home";
    }

    const formMinHeight = "390px";
</script>

<form class="w-2/3 h-4/5 flex justify-center items-center flex-col">
    <div
        class="mt-5 p-6 w-full max-w-sm bg-white rounded-lg shadow-md flex flex-col justify-center"
        style="min-height: {formMinHeight};"
    >
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
        <DefaultButton isWhite={null} onClickHandler={clicklg}
            >{!loginState ? "Entrar" : "Registrar"}</DefaultButton
        >
    </div>
</form>
