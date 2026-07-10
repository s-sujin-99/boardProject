<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>sol3</title>
  <style>

    section {
      width: 90%;
      margin: 0 auto;
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 10px;
    }

    h1,
    p.p1 {
      color: gray;
    }

    div {
      border: 1px solid rgb(0, 0, 0, 0.2);
      /* box-shadow: rgba(0, 0, 0, 0.16) 0px 10px 36px 0px, rgba(0, 0, 0, 0.06) 0px 0px 0px 1px; */
      border-radius: 20px;
    }

    div>img {
      padding-top: 15px;
      width: 150px;
      height: 150px;
      border-radius: 50%;
    }

    a {
      text-decoration: none;
    }

    a:hover {
      border-radius: 20px;
      background-color: salmon;
    }

    a:link,
    a:visited {
      color: black;
    }
  </style>
</head>

<body>
  <main>
    <h1>여가 시간 즐기기</h1>
    <p class="p1">Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse, est.</p>
    <section>
      <a href="./sol.html" target="_blank">
        <div>
          <img src="../images/tr-1.png" alt="1">
          <h4>도심 투어</h4>
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis, debitis!</p>
        </div>
      </a>
      <a href="./sol2.html" target="_blank">
        <div>
          <img src="../images/tr-2.png" alt="1">
          <h4>바다 즐기기</h4>
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis, debitis!</p>
        </div>
      </a>
      <a href="../../07텍스트스타일/260513/sol_1.html" target="_blank">
        <div>
          <img src="../images/tr-3.png" alt="1">
          <h4>등산 도전하기</h4>
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis, debitis!</p>
        </div>
      </a>
      <a href="../../07텍스트스타일/260513/sol_2.html" target="_blank">
        <div>
          <img src="../images/tr-4.png" alt="1">
          <h4>관광지 투어</h4>
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis, debitis!</p>
        </div>
      </a>
    </section>
  </main>
</body>

</html>