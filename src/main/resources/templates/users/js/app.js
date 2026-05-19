// Load component
async function loadComponent(id, file) {
    const response = await fetch(file);
    const data = await response.text();
    document.getElementById(id).innerHTML = data;
}

// Load page
async function loadPage(page) {
    const response = await fetch(page);
    const data = await response.text();
    document.getElementById("content").innerHTML = data;
}

// Load khi mở web
window.onload = async () => {

    // Load layout
    await loadComponent("header", "header.html");
    await loadComponent("footer", "footer.html");

    // Page mặc định
    await loadPage("home.html");

    // Lấy token
    const token = localStorage.getItem("token");

    // Nếu chưa login → chuyển hướng
    if (!token) {
        console.log("Chưa có token!");

        setTimeout(() => {
            window.location.href = "login.html";
        }, 1000);

        return;
    }

};


//
//    // Gọi API lấy user
//    try {
//        const response = await fetch("http://localhost:8080/auth/me", {
//            method: "GET",
//            headers: {
//                "Authorization": "Bearer " + token
//            }
//        });
//
//        const user = await response.json();
//
//        console.log(user);
//
//        // Hiển thị tên lên header
//        const usernameEl = document.getElementById("username");
//
//        if (usernameEl) {
//            usernameEl.innerText = user.name;
//        }
//
//    } catch (error) {
//        console.error("Lỗi lấy user:", error);
//    }