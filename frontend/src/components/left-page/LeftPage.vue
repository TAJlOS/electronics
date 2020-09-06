<template>
  <div class="left-page">
    <Sorting></Sorting>
    <div class="filters">
      <div v-for="parameter in PARAMETER_LIST" v-bind:key="parameter.name">
        <InputFilter
          v-if="parameter.type === 'input'"
          v-bind:parameter="parameter"
          ref="'param'+parameter.name"
        ></InputFilter>
        <CheckboxFilter
          v-else
          v-bind:parameter="parameter"
          ref="'param'+parameter.name"
        ></CheckboxFilter>
      </div>
      <FilterButtons
        v-on:apply-parameters="applyParameters"
        v-on:reset-parameters="resetParameters"
      ></FilterButtons>
    </div>
  </div>
</template>

<script>
import InputFilter from "@/components/left-page/InputFilter";
import CheckboxFilter from "@/components/left-page/CheckboxFilter";
import FilterButtons from "@/components/left-page/FilterButtons";
import { mapGetters } from "vuex";
import Sorting from "@/components/left-page/Sorting";
export default {
  name: "left-page",
  components: { Sorting, FilterButtons, CheckboxFilter, InputFilter },
  computed: {
    ...mapGetters(["PARAMETER_LIST"])
  },
  methods: {
    getFilterRequest() {
      let request = "";
      const data = this.$store.state.left_page.parameters;
      const parameters_state = this.$store.state.left_page.parameters_state;
      let count = data.length;
      let cur = 1;
      for (let index in data) {
        if (data[index].type === "input") {
          if (
            data[index].min !== parameters_state[data[index].name].cur_min ||
            data[index].max !== parameters_state[data[index].name].cur_max
          ) {
            request +=
              "min_" +
              data[index].field +
              "=" +
              parameters_state[data[index].name].cur_min +
              "&";
            request +=
              "max_" +
              data[index].field +
              "=" +
              parameters_state[data[index].name].cur_max +
              "&";
          }
        }
        if (data[index].type === "checkbox") {
          if (parameters_state[data[index].name].checkedList.length !== 0) {
            request += data[index].field + "=";

            for (
              let i = 0;
              i < parameters_state[data[index].name].checkedList.length;
              i++
            ) {
              request += parameters_state[data[index].name].checkedList[i];

              if (
                i !==
                parameters_state[data[index].name].checkedList.length - 1
              ) {
                request += ",";
              }
            }
            if (cur !== count) {
              request += "&";
            }
          }
        }
        cur++;
      }
      return request;
    },
    applyParameters() {
      let request = this.getFilterRequest();
      this.$store.commit("SET_FILTERS_REQUEST", request);
      this.$store.commit("TO_PAGE", 1);
      this.$store.dispatch("GET_PRODUCT_LIST");
      this.$store.dispatch("GET_PARAMETER_LIST");
    },
    resetParameters() {
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
.filters {
  border-radius: 10px;
  border-color: rgb(206, 201, 201);
  border-width: 1px;
  border-style: solid;
  margin: 20px;
  padding: 20px;
  width: 270px;
  font-family: "PT Sans", "Helvetica", "Arial", sans-serif;
  font-size: 16px;
  color: black;
}
</style>
