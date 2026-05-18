// Load component
async function loadComponent(id, file){

    const response = await fetch(file);

    const data = await response.text();

    document.getElementById(id).innerHTML = data;
}

// Load page
async function loadPage(page){

    const response = await fetch(page);

    const data = await response.text();

    document.getElementById("content").innerHTML = data;
}

// Load khi mở web
window.onload = async () => {

    // Header
    await loadComponent("header", "header.html");

    // Footer
    await loadComponent("footer", "footer.html");

    // Page mặc định
    await loadPage("home.html");
}