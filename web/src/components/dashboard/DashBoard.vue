<template>
  <div>
    <board-item class="board-item" title="나의 총 지출액">
      <v-row>
          <v-col cols="12" sm="6">
            <v-data-table
              :headers="headers"
              :items="desserts"
              :items-per-page="5"
              class="elevation-1"
            >
            </v-data-table>
          </v-col>
          <v-col cols="12" sm="6" style="display: flex; align-items: center;">
            <Bar style="width: 100%; height: 300px;"  :data="data" :options="options" />
          </v-col>
        </v-row>
    </board-item>
    <board-item class="board-item" title="최근 N 개월동안 가계부에서 가장 많이 나온 단어">
      <v-row>
        <v-col cols="12" sm="7">
          <word-cloud
            :data="defaultWords"
            nameKey="name"
            valueKey="value"
            :color="myColors"
            :showTooltip="false"
            :wordClick="wordClickHandler">
          </word-cloud>
        </v-col>
        <v-col cols="12" sm="5" class="d-flex flex-column justify-space-between">
          <div >
            <label 
              style="color: var(--v-primary-base); font-size: 20px; font-weight: bold;">
              최근 N개월 동안 가계부에서 가장 많이 나온 단어들을 보여드릴게요
            </label>
            <br/>
            <br/>
            <label style="font-size: 12px; line-height: 25px;">
              최근 가장 많이 나온 단어는 {{ defaultWords.map(x => `${x.name}(${x.value})`).join(", ") }}네요.
              소비를 줄이기 위해 {{ defaultWords[0].name }}에 대한 지출을 줄여보시는건 어떠실까요? 
            </label>
          </div>
          <div class="d-flex justify-end" style="margin: 20px 0px;">
            <v-btn color="primary" width="100%" class="d-flex align-center "> 
              <v-icon left>mdi-download</v-icon>워드클라우드 다운로드 
            </v-btn>
          </div>
        </v-col>

      </v-row>
    </board-item>
    <board-item class="board-item" title="N개월 이후 예상 소비">
      {{$cookies.get("userNm")}}님의 소비를 분석하여 N개월까지의 예상 수치를 보여드릴께요!
      <LineChart :data="chart.data" id="line-chart"/>
    </board-item>
    </div>
</template>

<script lang="ts">
import {
BarElement,
CategoryScale,
Chart as ChartJS,
Legend,
LineElement,
LinearScale,
PointElement,
Title,
Tooltip
} from 'chart.js';
import Vue from 'vue';
import { Bar, Line } from 'vue-chartjs';
import WordCloud from 'vue-wordcloud';
import BoardItem from './components/BoardItem.vue';
ChartJS.register(CategoryScale, LinearScale, BarElement,LineElement, PointElement, Title, Tooltip, Legend)
export default Vue.extend({
  components: {BoardItem, Bar, WordCloud, LineChart: Line}, 
  beforeMount(){
    this.defaultWords = this.defaultWords.sort((x, y) => y.value - x.value)
  }, 
  methods:{
    wordClickHandler(name, value, vm) {
      console.log('wordClickHandler', name, value, vm);
    },
    myStyles(){
      return {
        width: `10000px`,
      }
    }
  },
  data() {
    return {
      myColors: ['#1f77b4', '#629fc9', '#94bedb', '#c9e0ef'],
      chart: {
        data: {
          labels: [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023],
          datasets: [
            {
              label: "인원수",
              backgroundColor: "rgb(255,153,204, 0.5)",
              pointBackgroundColor: "hotpink",
              fill: true,
              tension: 0.1,
              borderColor: "hotpink",
              pointBorderColor: "hotpink",
              borderWidth: 1,
              pointBorderWidth: 1,
              data: [450, 300, 100, 1000, 750, 600, 900, 1500, 1200, 2000]
            }
          ]
        },
      },
      defaultWords: [
        {
          "name": "Cat",
          "value": 26
        },
        {
          "name": "fish",
          "value": 19
        },
        {
          "name": "things",
          "value": 18
        },
        {
          "name": "look",
          "value": 16
        },
        {
          "name": "two",
          "value": 15
        },
        {
          "name": "fun",
          "value": 9
        },
        {
          "name": "know",
          "value": 9
        },
        {
          "name": "good",
          "value": 9
        },
        {
          "name": "play",
          "value": 6
        }
      ],
      headers: [
          {
            text: 'Dessert (100g serving)',
            align: 'start',
            sortable: false,
            value: 'name',
          },
          { text: 'Calories', value: 'calories' },
          { text: 'Fat (g)', value: 'fat' },
          { text: 'Carbs (g)', value: 'carbs' },
          { text: 'Protein (g)', value: 'protein' },
          { text: 'Iron (%)', value: 'iron' },
        ],
        desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
            fat: 6.0,
            carbs: 24,
            protein: 4.0,
            iron: 1,
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
            fat: 9.0,
            carbs: 37,
            protein: 4.3,
            iron: 1,
          },
          {
            name: 'Eclair',
            calories: 262,
            fat: 16.0,
            carbs: 23,
            protein: 6.0,
            iron: 7,
          },
          {
            name: 'Cupcake',
            calories: 305,
            fat: 3.7,
            carbs: 67,
            protein: 4.3,
            iron: 8,
          },
          {
            name: 'Gingerbread',
            calories: 356,
            fat: 16.0,
            carbs: 49,
            protein: 3.9,
            iron: 16,
          },
          {
            name: 'Jelly bean',
            calories: 375,
            fat: 0.0,
            carbs: 94,
            protein: 0.0,
            iron: 0,
          },
          {
            name: 'Lollipop',
            calories: 392,
            fat: 0.2,
            carbs: 98,
            protein: 0,
            iron: 2,
          },
          {
            name: 'Honeycomb',
            calories: 408,
            fat: 3.2,
            carbs: 87,
            protein: 6.5,
            iron: 45,
          },
          {
            name: 'Donut',
            calories: 452,
            fat: 25.0,
            carbs: 51,
            protein: 4.9,
            iron: 22,
          },
          {
            name: 'KitKat',
            calories: 518,
            fat: 26.0,
            carbs: 65,
            protein: 7,
            iron: 6,
          },
        ],
      data: {
        labels: ['January', 'February', 'March'],
        datasets: [{ data: [40, 20, 12] }]
      },
      options: {
        responsive: false
      }
    }
  }
})
</script>

<style lang="scss">
.board-item{
  margin-bottom: 30px;
}
</style>