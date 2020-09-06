<template>
  <div class="empty-product-list">
    <p class="p1">Ничего нет</p>
    <p class="p2">Попробуйте изменить критерии поиска</p>
    <button @click="resetFilterParameters">Сбросить фильтры</button>
  </div>
</template>

<script>
export default {
  name: "EmptyProductList",
  methods: {
    resetFilterParameters() {
      this.$store.commit("SET_FILTERS_REQUEST", "");
      this.$store.commit("TO_PAGE", 1);
      this.$store.commit("RESET_FILTER_VALUES");
      this.$store.dispatch("GET_PRODUCT_LIST");
      this.$store.dispatch("GET_PARAMETER_LIST");
    }
  }
};
</script>

<style scoped>
.empty-product-list {
  background: white;
  border: 1px solid #ddd;
  border-radius: 15px;
  width: 700px;
  margin-left: 20px;
  margin-top: 20px;
  text-align: center;
}
.p1 {
  margin-top: 100px;
  margin-bottom: 20px;
  font-size: 24px;
}
.p2 {
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 16px;
}
button {
  outline: none;
  background: white;
  border: 1px solid #ddd;
  border-radius: 5px;
  color: black;
  cursor: pointer;
  box-sizing: border-box;
  margin-bottom: 50px;
  margin-top: 50px;
  padding: 10px 50px;
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
