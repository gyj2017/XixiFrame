<template>
  <el-breadcrumb separator="/">


    <el-breadcrumb-item v-for="(item, index) in breadcrumbList">{{ item.name }}
    <span v-if="parentName && index > 0">{{parentName}}&nbsp;&nbsp;/&nbsp;&nbsp;</span>
    <span v-if="index==breadcrumbList.length-1">{{parentName}}&nbsp;&nbsp;/&nbsp;&nbsp;</span>
    <span v-else>{{item.name}}</span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import {useRoute} from 'vue-router'
import {watch} from "vue";
import { ref, watch } from 'vue'

const breadcrumbList = ref([]);

const route = useRoute();
const parentName = ref("");
const initBreadcrumbList=()=>{
  breadcrumbList.value = route.matched;
}


watch(route,)

</script>

<style lang="sass" scoped>

</style>
