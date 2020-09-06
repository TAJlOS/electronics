<template>
  <div class="filter">
    <a class="filter-name" @click="changeFlag">
      <i v-bind:class="[flag ? 'arrow down' : 'arrow up']"></i>
      <span
        ref="#"
        v-bind:style="{ 'font-weight': [flag ? 'bold' : 'normal'] }"
        >{{ this.parameter.name }}</span
      >
    </a>
    <div v-if="flag" class="input-filter">
      <div class="input-filter-parameter-area">
        <input class="input-parameter" type="text" @mouseout="mouseoutMinInput" v-model="minValue" />
      </div>
      <div class="input-filter-parameter-area">
        <input class="input-parameter" type="text" @mouseout="mouseoutMaxInput" v-model="maxValue" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["parameter"],
  data() {
    return {
      flag: null
    };
  },
  created() {
    /*let index = this.$store.state.left_page.parameters.indexOf(this.parameter);
    this.flag = this.$store.state.left_page.parameters[index].open;*/
      this.flag = this.$store.state.left_page.parameters_state[this.parameter.name].open;
  },
  name: "InputFilter",
  computed: {
    minValue: {
      get() {
        /*let index = this.$store.state.left_page.parameters.indexOf(
          this.parameter
        );
        return "от " + this.$store.state.left_page.parameters[index].cur_min;*/
          return "от " + this.$store.state.left_page.parameters_state[this.parameter.name].cur_min;
      },
      set(newValue) {
        const number = Number(newValue);
        let index = this.$store.state.left_page.parameters.indexOf(
          this.parameter
        );
        if (
          !isNaN(number) &&
          Number.isInteger(number) &&
          number >= this.$store.state.left_page.parameters[index].min &&
          //number <= this.$store.state.left_page.parameters[index].cur_max
            number <= this.$store.state.left_page.parameters_state[this.parameter.name].cur_max
        ) {
          this.$store.commit("SET_INPUT_FILTER_VALUES", {
            name: this.parameter.name,
            min: number,
            //max: this.$store.state.left_page.parameters[index].max
            max: this.$store.state.left_page.parameters_state[this.parameter.name].cur_max
          });
        }
      }
    },
    maxValue: {
      get() {
        /*let index = this.$store.state.left_page.parameters.indexOf(
          this.parameter
        );
        return "до " + this.$store.state.left_page.parameters[index].cur_max;*/
          return "до " + this.$store.state.left_page.parameters_state[this.parameter.name].cur_max;
      },
      set(newValue) {
        const number = Number(newValue);
        let index = this.$store.state.left_page.parameters.indexOf(
          this.parameter
        );
        if (
          !isNaN(number) &&
          Number.isInteger(number) &&
          //number >= this.$store.state.left_page.parameters[index].cur_min &&
          number >= this.$store.state.left_page.parameters_state[this.parameter.name].cur_min &&
          number <= this.$store.state.left_page.parameters[index].max
        ) {
          this.$store.commit("SET_INPUT_FILTER_VALUES", {
            name: this.parameter.name,
            //min: this.$store.state.left_page.parameters[index].min,
            min: this.$store.state.left_page.parameters_state[this.parameter.name].cur_min,
            max: number
          });
        }
      }
    }
  },
  methods: {
    changeFlag: function() {
      this.flag = !this.flag;
      this.$store.commit("SET_FILTER_FLAG", {
        name: this.parameter.name,
        flag: this.flag
      });
    },
      mouseoutMinInput(){

      },
      mouseoutMaxInput() {
      }
  }
};
</script>

<style scoped>
.filter {
  padding: 0;
  width: 100%;
  box-sizing: border-box;
}
.filter-name:hover {
  background-color: lightcoral;
}
.input-filter {
  display: flex;
  width: 100%;
}
.input-filter-parameter-area {
  margin: 15px 5px 5px 15px;
  width: 50%;
}
.input-parameter {
  background-color: white;
  border-radius: 8px;
  outline: none;
  padding-left: 12px;
  padding-top: 10px;
  padding-bottom: 10px;
  border-width: 1px;
  border-style: solid;
  border-color: rgb(206, 201, 201);
  width: calc(100% - 12px);
}
.filter-name {
  display: block;
  cursor: pointer;
  padding: 8px 0px 8px 15px;
  user-select: none;
  text-align: left;
}
.arrow {
  border: solid black;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
}

.up {
  transform: rotate(-135deg);
  -webkit-transform: rotate(-135deg);
}

.down {
  transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
}
span {
  padding-left: 5px;
}
</style>
