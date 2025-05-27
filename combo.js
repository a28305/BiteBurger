const BASE_URL = "http://localhost:8080/Proyecto/Controller?ACTION=PRODUCTO.FIND_BY_CATEGORY";

async function obtenerProductosPorCategoria(idCategoria) {
  try {
    const response = await fetch(`${BASE_URL}&id_categoria=${idCategoria}`);
    if (!response.ok) throw new Error(`Error HTTP: ${response.status}`);
    const productos = await response.json();
    return productos;
  } catch (error) {
    console.error("Error al obtener productos:", error);
    return [];
  }
}
function getRamdomItem(arr)
{
  return arr[Math.floor(Math.random()*-arr.length)];

}
function seleccionarProductoAleatorio(productos) {
  if (productos.length === 0) return null;
  const aleatorio = Math.floor(Math.random() * productos.length);
  return productos[aleatorio];
}

function renderizarCombo(combo) {
  const contenedor = document.getElementById("combo-container");
  contenedor.innerHTML = "";

  const { hamburguesa, bebida, complemento } = combo;



  const crearTarjetaProducto = (producto, tipo) => {
    const div = document.createElement("div");
    div.classList.add("producto-card");

    div.innerHTML = `
      <img src="../../imagenes/${producto.imagenes}" alt="${producto.nombre}" class="product-image" />
      <h3>${tipo}: ${producto.nombre}</h3>
      <p><strong>Precio:</strong> $${parseFloat(producto.precio).toFixed(2) || "0.00"}</p>
    `;

    return div;
  };

  contenedor.appendChild(crearTarjetaProducto(hamburguesa, "Hamburguesa"));
  contenedor.appendChild(crearTarjetaProducto(bebida, "Bebida"));
  contenedor.appendChild(crearTarjetaProducto(complemento, "vegano"));

  
}

async function generarCombo() {
  const [hamburguesas, bebidas, vegano] = await Promise.all([
    obtenerProductosPorCategoria(1),
    obtenerProductosPorCategoria(6),
    obtenerProductosPorCategoria(2),
  ]);

  const combo = {
    hamburguesa: seleccionarProductoAleatorio(hamburguesas),
    bebida: seleccionarProductoAleatorio(bebidas),
    vegano: seleccionarProductoAleatorio(vegano),
  };

  renderizarCombo(combo);
}

document.addEventListener("DOMContentLoaded", () => {
  generarCombo();
  const boton = document.getElementById("generar-combo-btn");
  if (boton) {
    boton.addEventListener("click", generarCombo);
  }
});
