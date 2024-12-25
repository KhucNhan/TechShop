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
<%--        <li class="nav-item">--%>
<%--          <a class="nav-link" href="/products?action=#">Home</a>--%>
<%--        </li>--%>
        <li class="nav-item">
          <a class="nav-link" href="/users?action=products">Product</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/products?action=users">User</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="orders">Order</a>
        </li>
        <li style="position: relative; left: 100px;" class="nav-item align-items-center">
          <form action="/search" style="margin: 0" class="d-flex align-items-center" role="search">
            <input style="width: 40rem; background-color: lightgrey; border:1px solid black"
                   class="form-control me-2" type="search" placeholder="Search"
                   aria-label="Search" name="value">
              <input name="servletPath" hidden="hidden" value="${pageContext.request.servletPath}">
            <button class="btn btn-success" type="submit">Search</button>
          </form>
        </li>
        <li class="nav-item dropdown d-flex" style="position: relative; left: 250px;">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
             aria-expanded="false">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                 class="bi bi-gear" viewBox="0 0 16 16">
              <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492M5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0"/>
              <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115z"/>
            </svg>
          </a>
          <ul class="dropdown-menu" style="left: -75px;">
            <li><a style="color: black" class="dropdown-item" href="/users?action=updateInformation">Update account</a></li>
            <li><a style="color: black" class="dropdown-item" href="/users?action=changePassword">Change password</a></li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li><a style="color: black" class="dropdown-item" href="/authenticate?action=logout">Logout</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
