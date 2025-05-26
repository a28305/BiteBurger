// Definir listaProductos globalmente para evitar errores
const listaProductos = document.querySelector("#lista-carrito tbody");
const listaCompra = document.querySelector("#lista-compra tbody");

class Carrito {
    comprarProducto(e) {
        e.preventDefault();
        if (e.target.classList.contains("agregar-carrito")) {
            const producto = e.target.closest(".producto");
            this.leerDatosProducto(producto);
        }
    }

    leerDatosProducto(producto) {
        const infoProducto = {
            imagen: producto.querySelector("img").src,
            titulo: producto.querySelector("h4").textContent,
            precio: parseFloat(producto.querySelector(".precio span").textContent), // Convertimos a número
            id: producto.querySelector("a").getAttribute("data-id"),
            cantidad: 1,
        };

        let productosLS = this.obtenerProductosLocalStorage();
        if (productosLS.some((p) => p.id === infoProducto.id)) {
            Swal.fire({ icon: "info", title: "Oops...", text: "El producto ya está agregado" });
        } else {
            this.insertarCarrito(infoProducto);
        }
    }

    insertarCarrito(producto) {
        if (!listaProductos) {
            console.error("Error: listaProductos no existe en el DOM");
            return;
        }

        const row = document.createElement("tr");
        row.innerHTML = `
            <td><img src="${producto.imagen}" width="100"></td>
            <td>${producto.titulo}</td>
            <td>${producto.precio.toFixed(2)}</td>
            <td>
                <input type="number" class="form-control cantidad" min="1" value="${producto.cantidad}">
            </td>
            <td id="subtotales">${(producto.precio * producto.cantidad).toFixed(2)}</td>
            <td>
                <a href="#" class="borrar-producto fas fa-times-circle" data-id="${producto.id}"></a>
            </td>
        `;
        listaProductos.appendChild(row);
        this.guardarProductosLocalStorage(producto);
        this.calcularTotal();
    }

    eliminarProducto(e) {
        e.preventDefault();
        if (e.target.classList.contains("borrar-producto")) {
            const producto = e.target.closest("tr");
            producto.remove();
            this.eliminarProductoLocalStorage(producto.querySelector("a").getAttribute("data-id"));
            this.calcularTotal();
        }
    }

vaciarCarrito(e) {
    e.preventDefault();
    
    // Vaciar la lista de productos en el carrito
    const listaProductos = document.getElementById("lista-carrito");
    if (listaProductos) {
        listaProductos.innerHTML = "";
    } else {
        console.warn("Elemento 'lista-carrito' no encontrado en el DOM");
    }

    // Vaciar LocalStorage
    this.vaciarLocalStorage();

    // Verificar si los elementos existen antes de modificar innerHTML
    setTimeout(() => {
        const subtotal = document.getElementById("subtotal");
        const total = document.getElementById("total");
        const igv = document.getElementById("igv");

        if (subtotal) {
            subtotal.innerHTML = "S/. 0.00";
        } else {
            console.warn("Elemento 'subtotal' no encontrado en el DOM");
        }

        if (total) {
            total.value = "S/. 0.00";
        } else {
            console.warn("Elemento 'total' no encontrado en el DOM");
        }

        if (igv) {
            igv.innerHTML = "S/. 0.00";
        } else {
            console.warn("Elemento 'igv' no encontrado en el DOM");
        }
    }, 100);
}

    
    
    

    guardarProductosLocalStorage(producto) {
        let productos = this.obtenerProductosLocalStorage();
        productos.push(producto);
        localStorage.setItem("productos", JSON.stringify(productos));
    }

    obtenerProductosLocalStorage() {
        return JSON.parse(localStorage.getItem("productos")) || [];
    }

    leerLocalStorage() {
        if (!listaProductos) {
            console.error("Error: listaProductos no está en el DOM");
            return;
        }

        const productosLS = this.obtenerProductosLocalStorage();
        productosLS.forEach((producto) => this.insertarCarrito(producto));
    }

    leerLocalStorageCompra() {
        if (!listaCompra) {
            console.error("Error: listaCompra no está en el DOM");
            return;
        }

        const productosLS = this.obtenerProductosLocalStorage();
        productosLS.forEach((producto) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td><img src="${producto.imagen}" width="100"></td>
                <td>${producto.titulo}</td>
                <td>${producto.precio.toFixed(2)}</td>
                <td><input type="number" class="form-control cantidad" min="1" value="${producto.cantidad}"></td>
                <td id="subtotales">${(producto.precio * producto.cantidad).toFixed(2)}</td>
                <td>
                    <a href="#" class="borrar-producto fas fa-times-circle" data-id="${producto.id}"></a>
                </td>
            `;
            listaCompra.appendChild(row);
        });
        this.calcularTotal();
    }

    eliminarProductoLocalStorage(productoID) {
        let productosLS = this.obtenerProductosLocalStorage();
        productosLS = productosLS.filter((producto) => producto.id !== productoID);
        localStorage.setItem("productos", JSON.stringify(productosLS));
    }

    vaciarLocalStorage() {
        localStorage.removeItem("productos");
    }

procesarPedido(e) {
    e.preventDefault();
    if (this.obtenerProductosLocalStorage().length === 0) {
        Swal.fire({
            icon: "warning",
            title: "Your cart is empty",
            text: "Are you sure you want to continue?",
            showCancelButton: true,
            confirmButtonText: "Yes, continue",
            cancelButtonText: "Cancel"
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "../comprar/comprar.html";
            }
        });
    } else {
        window.location.href = "../comprar/comprar.html";
    }
}



    calcularTotal() {
        const productosLS = this.obtenerProductosLocalStorage();
        let total = productosLS.reduce((acc, p) => acc + p.precio * p.cantidad, 0);
        let igv = (total * 0.18).toFixed(2);
        let subtotal = (total - igv).toFixed(2);

        document.getElementById("subtotal").innerText = `S/. ${subtotal}`;
        document.getElementById("igv").innerText = `S/. ${igv}`;
        document.getElementById("total").value = `S/. ${total.toFixed(2)}`;
    }
}
