const loginBtn = document.getElementById('loginBtn');
const registerBtn = document.getElementById('registerBtn');
const registroCampos = document.getElementById('registroCampos');
const botonesIniciales = document.querySelector('.botones-iniciales');

function componentForm(login) {
    const pDiv = document.createElement("div");

    const titulo = document.createElement("h2");
    titulo.textContent = login ? "Iniciar sesión" : "Registrarse";

    const inputUsuario = document.createElement("input");
    inputUsuario.type = "text";
    inputUsuario.placeholder = login ? "Usuario" : "Crear usuario";
    inputUsuario.classList.add("input-registro");

    const inputCorreo = document.createElement("input");
    inputCorreo.type = "email";
    inputCorreo.placeholder = "Correo electrónico";
    inputCorreo.classList.add("input-registro", "ce-input");

    const inputClave = document.createElement("input");
    inputClave.type = "password";
    inputClave.placeholder = login ? "Contraseña" : "Crear contraseña";
    inputClave.classList.add("input-registro", "cc-input");

    const btnPrincipal = document.createElement("button");
    btnPrincipal.textContent = login ? "Ingresar" : "Crear cuenta";
    btnPrincipal.classList.add("btn");

    const btnAtras = document.createElement("button");
    btnAtras.textContent = "Atrás";
    btnAtras.classList.add("btn");
    btnAtras.id = "atrasBtn";

    pDiv.appendChild(titulo);
    pDiv.appendChild(inputUsuario);
    pDiv.appendChild(inputCorreo);
    pDiv.appendChild(inputClave);
    pDiv.appendChild(btnPrincipal);
    pDiv.appendChild(btnAtras);

    // api consume
    // register
    btnPrincipal.onclick = async () => {
        let x = await fetch(`http://localhost:8080/${login ? "loggin" : "register"}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: inputUsuario.value,
                password: inputClave.value,
                email: inputCorreo.value
            })
        });
        if (x.ok) {
            if (login) {
                window.location.replace("https://google.com");
                return;
            }
            window.alert("Register hecho");
            registroCampos.classList.remove('visible');
            registroCampos.innerHTML = '';
            botonesIniciales.style.display = 'block';
        }
    }
    return pDiv;
}

function mostrarFormulario(esLogin) {
    botonesIniciales.style.display = 'none';
    registroCampos.innerHTML = '';
    registroCampos.classList.add('visible');
    registroCampos.appendChild(componentForm(esLogin));

    const btnAtras = document.getElementById('atrasBtn');
    btnAtras.addEventListener('click', () => {
        registroCampos.classList.remove('visible');
        registroCampos.innerHTML = '';
        botonesIniciales.style.display = 'block';
    });
}

registerBtn.addEventListener('click', () => {
    mostrarFormulario(false); // Registro
});

loginBtn.addEventListener('click', () => {
    mostrarFormulario(true); // Login
});
