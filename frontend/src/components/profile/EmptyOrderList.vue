<template>
  <div class="empty-order-list">
    <p class="p1">У вас ещё нет заказов</p>
  </div>
</template>

<script>
export default {
  name: "EmptyOrderList"
};
</script>

<style scoped>
.empty-order-list {
  background: white;
  border: 1px solid #ddd;
  border-radius: 15px;
  width: 700px;
  margin: 100px auto;
  text-align: center;
}
.p1 {
  margin: 25% auto;
  font-size: 24px;
}
</style>
