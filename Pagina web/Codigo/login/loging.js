document.addEventListener("DOMContentLoaded", () => {
    const formLogin = document.getElementById("formLogin");

    if (formLogin) {
        formLogin.addEventListener("submit", async (e) => {
            e.preventDefault();

            const email = formLogin.querySelector("input[name='correo']").value.trim();
            const password = formLogin.querySelector("input[name='contrasena']").value.trim();

            // Validación de campos vacíos
            if (!email || !password) {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Please fill in all fields!"
                });
                return;
            }

            try {
                const response = await fetch("LoginServlet", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: `correo=${encodeURIComponent(email)}&contrasena=${encodeURIComponent(password)}`
                });

                if (response.ok) {
                    const result = await response.json(); // Recibir la respuesta del servidor
                    
                    if (result.success) {
                        localStorage.setItem("user", email); // Guardar sesión
                        Swal.fire({
                            icon: "success",
                            title: "Welcome!",
                            text: "Login successful!",
                            showConfirmButton: false,
                            timer: 2000
                        });

                        setTimeout(() => {
                            window.location.href = "../principal/index.html"; // Redirigir tras éxito
                        }, 2000);
                    } else {
                        Swal.fire({
                            icon: "error",
                            title: "Invalid Credentials",
                            text: result.message || "Incorrect email or password!"
                        });
                    }
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Error!",
                        text: "Server connection failed. Try again later."
                    });
                }
            } catch (error) {
                console.error("Login error:", error);
                Swal.fire({
                    icon: "error",
                    title: "Network Error!",
                    text: "Couldn't connect to the server."
                });
            }
        });
    }

    // Verificar si el usuario ya está logueado
    if (localStorage.getItem("user")) {
        document.getElementById("btnLogin").textContent = "Logout";
        document.getElementById("btnLogin").addEventListener("click", () => {
            localStorage.removeItem("user");
            Swal.fire({
                icon: "info",
                title: "Logged Out",
                text: "You have been logged out successfully.",
                showConfirmButton: false,
                timer: 2000
            });

            setTimeout(() => {
                window.location.href = "loging.html";
            }, 2000);
        });
    }
});
