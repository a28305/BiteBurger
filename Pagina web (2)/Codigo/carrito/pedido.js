document.addEventListener("DOMContentLoaded", () => {
    const carro = new Carrito();
    const carrito = document.getElementById("carrito");
    const productos = document.getElementById("lista-productos");
    const listaProductos = document.querySelector("#lista-carrito tbody");
    const vaciarCarritoBtn = document.getElementById("vaciar-carrito");
    const procesarPedidoBtn = document.getElementById("procesar-pedido");

    if (productos) productos.addEventListener("click", (e) => carro.comprarProducto(e));
    if (carrito) carrito.addEventListener("click", (e) => carro.eliminarProducto(e));
    if (vaciarCarritoBtn) vaciarCarritoBtn.addEventListener("click", (e) => carro.vaciarCarrito(e));
    if (procesarPedidoBtn) procesarPedidoBtn.addEventListener("click", (e) => carro.procesarPedido(e));

    if (listaProductos) carro.leerLocalStorage();
});
document.addEventListener("DOMContentLoaded", () => {
    const carritoSection = document.getElementById("carrito-section");
    const carritoIcon = document.querySelector(".btn2 img"); // Asegurando que seleccionamos la imagen dentro de .btn2

    if (carritoIcon && carritoSection) {
        carritoIcon.addEventListener("click", () => {
            console.log("Clic detectado en el carrito");
            carritoSection.style.display = carritoSection.style.display === "none" ? "block" : "none";
        });
    }
});



