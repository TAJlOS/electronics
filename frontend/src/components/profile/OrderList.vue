<template>
  <div class="order-list-wrapper">
    <div v-if="orderList.length !== 0" class="order-list">
      <OrderListItem
        v-for="order in orderList"
        v-bind:key="order.id"
        v-bind:order="order"
      ></OrderListItem>
    </div>
    <div v-else>
      <EmptyOrderList></EmptyOrderList>
    </div>
  </div>
</template>

<script>
import OrderListItem from "@/components/profile/OrderListItem";
import EmptyOrderList from "@/components/profile/EmptyOrderList";
export default {
  name: "OrderList",
  components: { EmptyOrderList, OrderListItem },
  computed: {
    orderList() {
      return this.$store.state.userOrdersList;
    }
  }
};
</script>

<style scoped>
.order-list-wrapper {
  display: flex;
  justify-content: center;
}
.order-list {
  display: flex;
  flex-direction: column;
}
</style>
