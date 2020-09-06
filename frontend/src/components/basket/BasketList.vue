<template>
  <div class="basket-list-page">
    <div class="basket">
      <p><span>Корзина</span></p>
      <div v-if="!IS_BASKET_LIST_EMPTY" class="basket-list">
        <div>
          <BasketListItem
            v-for="product in BASKET_LIST"
            v-bind:key="product.id"
            v-bind:product="product"
          >
          </BasketListItem>
        </div>
        <OrderAcceptPage></OrderAcceptPage>
      </div>
      <div v-else>
        <EmptyBasketPage></EmptyBasketPage>
      </div>
    </div>
  </div>
</template>

<script>
import BasketListItem from "@/components/basket/BasketListItem";
import { mapGetters } from "vuex";
import EmptyBasketPage from "@/components/basket/EmptyBasketPage";
import OrderAcceptPage from "@/components/basket/OrderAcceptPage";
export default {
  name: "BasketList",
  components: { OrderAcceptPage, EmptyBasketPage, BasketListItem },
  computed: {
    ...mapGetters(["BASKET_LIST", "IS_BASKET_LIST_EMPTY"])
  }
};
</script>

<style scoped>
.basket-list-page {
  display: flex;
  justify-content: center;
  align-content: space-between;
  align-items: flex-start;
  flex-direction: row;
}
.basket {
  display: flex;
  flex-direction: column;
}
p {
  margin: 20px 0 0 20px;
}
span {
  font-size: 24px;
  font-weight: bold;
}
.basket-list {
  display: inline-flex;
  flex-direction: row;
}
</style>
