<template>
  <div>
    <Header></Header>
    <div class="order-page">
      <p>Заказ успешно создан</p>
      <button><router-link to="/">Вернуться на главную</router-link></button>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "OrderSuccessful",
  components: { Footer, Header }
};
</script>

<style scoped>
.order-page {
  background: white;
  border: 1px solid #ddd;
  width: 700px;
  height: 300px;
  text-align: center;
  border-radius: 20px;
  margin: 100px auto;
}
p {
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
  margin-top: 30px;
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
