const loginBtn = document.getElementById('loginBtn');
const registerBtn = document.getElementById('registerBtn');
const registroCampos = document.getElementById('registroCampos');
const botonesIniciales = document.querySelector('.botones-iniciales');

registerBtn.addEventListener('click', () => {
    botonesIniciales.style.display = 'none';

    registroCampos.classList.add('visible');
    registroCampos.innerHTML = `
        <h2>Registrarse</h2>

        <input type="text" placeholder="Crear usuario" class="input-registro">
        <input type="email" placeholder="Correo electrónico" class="input-registro ce-input">
        <input type="password" placeholder="Crear contraseña" class="input-registro cc-input">
        <button class="btn">Crear cuenta</button>
        <button class="btn" id="atrasBtn">Atrás</button>
    `;

    const atrasBtn = document.getElementById('atrasBtn');
    atrasBtn.addEventListener('click', () => {
        registroCampos.classList.remove('visible');
        registroCampos.innerHTML = '';
        botonesIniciales.style.display = 'block';
    });
});

loginBtn.addEventListener('click', () => {
    botonesIniciales.style.display = 'none';

    registroCampos.classList.add('visible');
    registroCampos.innerHTML = `
        <h2>Iniciar sesión</h2>
        <br>
        <input type="text" placeholder="Usuario" class="input-registro usr-input">
        <input type="password" placeholder="Contraseña" class="input-registro cc-input">
        <button class="btn">Ingresar</button>
        <button class="btn" id="atrasBtn">Atrás</button>
    `;

    const atrasBtn = document.getElementById('atrasBtn');
    atrasBtn.addEventListener('click', () => {
        registroCampos.classList.remove('visible');
        registroCampos.innerHTML = '';
        botonesIniciales.style.display = 'block';
    });
});
