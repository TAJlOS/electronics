<template>
  <div class="pagination" v-if="TOTAL_PAGES > 1">
    <ul>
      <li><a href="#" @click="prevPage">«</a></li>
      <li
        v-for="page in Math.min(MAX_PAGES, TOTAL_PAGES)"
        :key="page"
        @click="toPage(getPage(page))"
        :style="{
          'background-color': [page === CURRENT_PAGE ? 'lightcoral' : 'white']
        }"
      >
        <a href="#">{{ getPage(page) }}</a>
      </li>
      <li><a href="#" @click="nextPage">»</a></li>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "Pagination",
  computed: {
    ...mapGetters(["TOTAL_PAGES", "CURRENT_PAGE", "MAX_PAGES"])
  },
  methods: {
    nextPage: function() {
      this.$store.commit("NEXT_PAGE");
      this.$store.dispatch("GET_PRODUCT_LIST");
    },
    prevPage: function() {
      this.$store.commit("PREV_PAGE");
      this.$store.dispatch("GET_PRODUCT_LIST");
    },
    toPage: function(page) {
      this.$store.commit("TO_PAGE", page);
      this.$store.dispatch("GET_PRODUCT_LIST");
    },
    getPage: function(page) {
      return (
        page +
        (this.CURRENT_PAGE <= this.MAX_PAGES
          ? 0
          : this.CURRENT_PAGE - this.MAX_PAGES)
      );
    }
  }
};
</script>

<style scoped>
.pagination {
  background-color: white;
  border-radius: 10px;
  border: 1px solid rgb(209, 207, 207);
  margin: 20px;
}

.pagination ul {
  display: flex;
  justify-content: center;
  padding: 0;
  margin: 0;
}
.pagination li {
  cursor: pointer;
  list-style-type: none;
  width: 65px;
  height: 49px;
  background-color: white;
  color: black;
  box-sizing: border-box;
  display: block;
}

.pagination li > a {
  text-decoration: none;
  color: inherit;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  display: block;
  line-height: 49px;
  text-align: center;
}
</style>
