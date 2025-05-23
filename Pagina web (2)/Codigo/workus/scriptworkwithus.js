// Botón para desplazarse a la sección "jobs"
document.addEventListener("DOMContentLoaded", function() {
  let scrollBtn = document.getElementById("scrollBtn");
  let jobsSection = document.getElementById("jobs");

  if (scrollBtn && jobsSection) { // Verificación de existencia
      scrollBtn.addEventListener("click", () => {
          jobsSection.scrollIntoView({ behavior: "smooth" });
      });
  } else {
      console.error("Error: No se encontró el botón 'scrollBtn' o la sección 'jobs'. Verifica los IDs.");
  }
});

// Campo dinámico para el método de contacto
document.addEventListener("DOMContentLoaded", function() {
  let contactoTipo = document.getElementById("contactoTipo");
  let contactoCampo = document.getElementById("contactoCampo");

  if (contactoTipo && contactoCampo) { // Verificación de existencia
      contactoTipo.addEventListener("change", () => {
          contactoCampo.innerHTML = "";

          let input = document.createElement("input");
          input.required = true;

          if (contactoTipo.value === "telefono") {
              input.type = "tel";
              input.id = "telefono";
              input.name = "telefono";
              input.placeholder = "Enter 9-digit phone number";
              input.pattern = "\\d{9}";
          } else if (contactoTipo.value === "email") {
              input.type = "email";
              input.id = "email";
              input.name = "email";
              input.placeholder = "Enter your email";
          }

          contactoCampo.appendChild(input);
      });
  } else {
      console.error("Error: No se encontró 'contactoTipo' o 'contactoCampo'. Verifica los IDs.");
  }
});

// Manejo de envío del formulario con validación
document.addEventListener("DOMContentLoaded", function() {
  let jobForm = document.getElementById("jobForm");

  if (jobForm) { // Verificación de existencia
      jobForm.addEventListener("submit", function(e) {
          let name = document.getElementById("name").value.trim();
          let email = document.getElementById("email") ? document.getElementById("email").value.trim() : "";
          let telefono = document.getElementById("telefono") ? document.getElementById("telefono").value.trim() : "";
          let message = document.getElementById("message").value.trim();

          if (!name || (!email && !telefono) || !message) {
              e.preventDefault(); // Bloquear el envío si hay campos vacíos
              alert("Por favor, completa todos los campos antes de enviar.");
          } else {
              alert("¡Solicitud enviada correctamente!");
          }
      });
  } else {
      console.error("Error: No se encontró el formulario 'jobForm'. Verifica el ID.");
  }
});

  //BOTON PAL VER MAS
  function mostrarOpciones() {
    let opciones = document.querySelector(".extra-opciones");
    opciones.style.display = opciones.style.display === "none" ? "block" : "none";
}
//para controlar el desplegable
document.getElementById("toggleMenu").addEventListener("click", function() {
  let menu = document.getElementById("menuOpciones");
  
  // Alterna entre mostrar y ocultar
  if (menu.style.display === "none" || menu.style.display === "") {
      menu.style.display = "block";
  } else {
      menu.style.display = "none";
  }
});

