// Obtener todos los productos desde la API
async function obtenerTodosLosProductos() {
  const url = "http://localhost:8080/Proyecto/Controller?ACTION=PRODUCTO.FIND_ALL";

  try {
    const response = await fetch(url);
    if (!response.ok) throw new Error(`Error HTTP: ${response.status}`);
    const productos = await response.json();
    return productos;
  } catch (error) {
    console.error("Error al obtener productos:", error);
    return [];
  }
}

// Elegir N productos aleatorios
function seleccionarProductosAleatorios(productos, cantidad = 5) {
  const copia = [...productos];
  const seleccionados = [];

  const max = Math.min(cantidad, copia.length);
  for (let i = 0; i < max; i++) {
    const index = Math.floor(Math.random() * copia.length);
    seleccionados.push(copia[index]);
    copia.splice(index, 1); // Eliminar para evitar repeticiones
  }

  return seleccionados;
}

// Mostrar los productos en el HTML (sin descripción)
function renderizarProductos(productos) {
  const contenedor = document.getElementById("productos-container");
  contenedor.innerHTML = "";

  productos.forEach(prod => {
    const div = document.createElement("div");
    div.classList.add("producto-card");

    div.innerHTML = `
      <img src="../../imagenes/${prod.imagenes}" alt="${prod.nombre}" class="product-image" />
      <h3>${prod.nombre}</h3>
      <p><strong>Precio:</strong> $${parseFloat(prod.precio).toFixed(2) || "0.00"}</p>
    `;

    contenedor.appendChild(div);
  });
}

// Ejecutar todo al cargar la página
async function mostrarCincoProductosAlAzar() {
  const productos = await obtenerTodosLosProductos();

  if (productos.length === 0) {
    document.getElementById("productos-container").innerText = "No hay productos disponibles.";
    return;
  }

  const cincoProductos = seleccionarProductosAleatorios(productos, 5);
  renderizarProductos(cincoProductos);
}

document.addEventListener("DOMContentLoaded", mostrarCincoProductosAlAzar);

// BOTÓN "VER MÁS"
function mostrarOpciones() {
  let opciones = document.querySelector(".extra-opciones");
  if (opciones) {
    opciones.style.display = opciones.style.display === "none" ? "block" : "none";
  }
}

// Control del menú desplegable mejorado
document.addEventListener("DOMContentLoaded", function () {
  let toggleMenu = document.getElementById("toggleMenu");
  let menu = document.getElementById("menuOpciones");

  if (toggleMenu && menu) {
    toggleMenu.addEventListener("click", function () {
      menu.style.display = menu.style.display === "none" || menu.style.display === "" ? "block" : "none";
    });
  } else {
    console.error("Error: No se encontró el menú desplegable. Verifica que los IDs sean correctos.");
  }
});
