<template>
  <main-viewer 
      title="대시보드"
      icon="mdi-monitor-dashboard"
      subTitle="모든 또는 그룹별 총액과 나의 월별, 일별 총액 등을 확인할 수 있어요!" >
    <board-item title="나의 총 지출액">
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
  </main-viewer>
</template>

<script lang="ts">
import {
BarElement,
CategoryScale,
Chart as ChartJS,
Legend,
LinearScale,
Title,
Tooltip
} from 'chart.js';
import Vue from 'vue';
import { Bar } from 'vue-chartjs';
import MainViewer from '../global/MainViewer.vue';
import BoardItem from './components/BoardItem.vue';
ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend)
export default Vue.extend({
  components: {MainViewer, BoardItem, Bar}, 
  methods:{
    myStyles(){
      return {
        width: `10000px`,
      }
    }
  },
  data() {
    return {
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

<style>

</style>