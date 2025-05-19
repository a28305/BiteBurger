// Simula login con verificación básica
document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('loginForm');
  if (loginForm) {
    loginForm.addEventListener('submit', e => {
      e.preventDefault();
      const dni = document.getElementById('dni').value;
      const password = document.getElementById('password').value;

      if (dni === '12345678A' && password === 'empleado') {
        localStorage.setItem('loggedIn', 'true');
        window.location.href = 'dashboard.html';
      } else {
        alert('DNI o contraseña incorrectos');
      }
    });
  }

  // Redirección si no está logueado
  if (window.location.pathname.includes('dashboard.html') && localStorage.getItem('loggedIn') !== 'true') {
    window.location.href = 'index.html';
  }
});

// Logout
function logout() {
  localStorage.removeItem('loggedIn');
  window.location.href = 'index.html';
}


