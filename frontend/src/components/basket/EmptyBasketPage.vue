<template>
  <div class="empty-basket-page">
    <p>Корзина пуста</p>
    <button><router-link to="/">Вернуться на главную</router-link></button>
  </div>
</template>

<script>
export default {
  name: "EmptyBasketPage"
};
</script>

<style scoped>
.empty-basket-page {
  background: white;
  border: 1px solid #ddd;
  width: 700px;
  height: 300px;
  margin-left: 20px;
  margin-top: 20px;
  text-align: center;
}
p{
  padding-top: 100px;
  font-size: 24px;
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
