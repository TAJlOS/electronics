<template>
  <div class="not-found-page-wrapper">
    <div class="not-found-page">
      <p>Страница не найдена</p>
      <button><router-link to="/">Вернуться на главную</router-link></button>
    </div>
  </div>
</template>

<script>
export default {
  name: "NotFoundPage",
  created() {
    document.title = "Страница не найдена";
  }
};
</script>

<style scoped>
.not-found-page-wrapper {
  width: 100%;
  height: 100%;
}
.not-found-page {
  background: white;
  border: 1px solid #ddd;
  width: 700px;
  height: 300px;
  text-align: center;
  box-sizing: border-box;
  margin-left: calc(50% - 350px);
  margin-top: 300px;
}
p {
  margin-top: 100px;
  font-size: 36px;
}
button {
  outline: none;
  background: white;
  border: 1px solid #ddd;
  padding: 0;
  border-radius: 5px;
  color: black;
  cursor: pointer;
  box-sizing: border-box;
  margin-bottom: 100px;
}

button:hover {
  background: rgb(125, 3, 3);
  background: linear-gradient(
    180deg,
    rgba(125, 3, 3, 1) 0%,
    rgba(204, 25, 25, 1) 32%,
    rgba(252, 102, 102, 1) 69%
  );
}
button:active {
  background: rgb(98, 5, 5);
  background: linear-gradient(
    180deg,
    rgba(98, 5, 5, 1) 0%,
    rgba(184, 25, 25, 1) 32%,
    rgba(240, 85, 85, 1) 69%
  );
}
a {
  text-decoration: none;
  color: black;
  padding: 10px 20px 10px 20px;
  display: block;
  box-sizing: border-box;
}
button a:hover {
  color: white;
}
</style>
