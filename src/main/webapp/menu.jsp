<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg fixed-top bg-body-tertiary mb-2">
    <div class="container-fluid">
        <a class="navbar-brand" href="/products?action=#">
            <svg xmlns="http://www.w3.org/2000/svg" width="32px" height="3rem" fill="currentColor" class="bi bi-laptop"
                 viewBox="0 0 16 16">
                <path d="M13.5 3a.5.5 0 0 1 .5.5V11H2V3.5a.5.5 0 0 1 .5-.5zm-11-1A1.5 1.5 0 0 0 1 3.5V12h14V3.5A1.5 1.5 0 0 0 13.5 2zM0 12.5h16a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 12.5"/>
            </svg>
            <span>Khuc Store</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 align-items-center">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/products?action=#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/products?action=phone">Phone</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/products?action=laptop">Laptop</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="orders">History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="statistical">Top seller</a>
                </li>
                <li style="position: relative; left: 60px;" class="nav-item align-items-center">
                    <form action="/search?servletPath=${pageContext.request.servletPath}" style="margin: 0"
                          class="d-flex align-items-center" role="search">
                        <input style="width: 40rem; background-color: lightgrey; border:1px solid black"
                               class="form-control me-2" type="search" placeholder="Search"
                               aria-label="Search" name="value" oninput="submitSearch()">
                        <button id="searchBtn" class="btn btn-success" type="submit">Search</button>
                    </form>
                </li>
                <li class="nav-item" style="position: relative; left: 167px;">
                    <a href="/cart?action=goToCart" class="nav-link">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                             class="bi bi-cart3" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l.84 4.479 9.144-.459L13.89 4zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                        </svg>
                    </a>
                </li>
                <li class="nav-item dropdown d-flex" style="position: relative; left: 167px;">
                    <a id="cartIcon" class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                             class="bi bi-gear" viewBox="0 0 16 16">
                            <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492M5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0"/>
                            <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115z"/>
                        </svg>
                    </a>
                    <ul class="dropdown-menu" style="left: -75px">
                        <li><a style="color: black" class="dropdown-item" href="/users?action=updateInformation">Update
                            account</a></li>
                        <li><a style="color: black" class="dropdown-item" href="/users?action=changePassword">Change
                            password</a></li>
                        <li><a style="color: black" class="dropdown-item" onclick="showPrompt()">Delete
                            account</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a style="color: black" class="dropdown-item" href="/authenticate?action=logout">Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <label for="cartIcon" class="rounded-circle text-center"
           style="background-color: red;position: fixed; right: 90px; top: 15px; height: 20px; width: 20px; color: white; font-weight: bold; font-size: 13px">${sessionScope['cartItemCount'] == null ? '0' : sessionScope['cartItemCount']}</label>
</nav>

<div class="modal fade" id="promptModal" tabindex="-1" aria-labelledby="promptModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="promptModalLabel" style="color: red">Delete account</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <label style="color: black; width: 100%">Are you sure to delete your account ?</label>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <a type="button" class="btn btn-primary" href="/users?action=deleteAccount">Delete</a>
            </div>
        </div>
    </div>
</div>

<script>
    // Hiển thị modal prompt
    function showPrompt() {
        const promptModal = new bootstrap.Modal(document.getElementById('promptModal'));
        promptModal.show();
    }

    function submitSearch() {
        setTimeout(() => {
            document.querySelector('form[role="search"]').submit();
        }, 1500);
    }
</script>
