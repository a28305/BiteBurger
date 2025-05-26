


  //BOTON PAL VER MAS
  function mostrarOpciones() {
    let opciones = document.querySelector(".extra-opciones");
    opciones.style.display = opciones.style.display === "none" ? "block" : "none";
}
//para controlar el desplegable
document.addEventListener("DOMContentLoaded", function () {
  let toggleMenu = document.getElementById("toggleMenu");
  let menu = document.getElementById("menuOpciones");

  if (toggleMenu && menu) { // Mejora: Verificar que los elementos existen antes de usarlos
      toggleMenu.addEventListener("click", function () {
          menu.style.display = menu.style.display === "none" || menu.style.display === "" ? "block" : "none";
      });
  } else {
      console.error("Error: No se encontró el menú desplegable. Verifica que los IDs sean correctos.");
  }
}); 


