<template>
  <el-dropdown>
    <span class="el-dropdown-link">
      <el-avatar shape="square" :size="40" :src="avatarUrl"/>
      &nbsp;&nbsp;{{currentUser.username}}
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>个人中心</el-dropdown-item>
        <el-dropdown-item @click="logout">安全退出</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup>


import {ref} from 'vue'
import store from '@/store';
import request, {getServerUrl} from "@/util/request";
import { ArrowDown } from '@element-plus/icons-vue'

const currentUser = ref(store.getters.GET_USER_INFO);
const avatarUrl = getServerUrl() +'/image/userAvatar/'+currentUser.value.avatar;

const logout =async () => {
  let result = await request.get("/logout");
  if (result.code == 200){
    store.dispatch('logout')
  }
}

</script>

<style lang="scss" scoped>
.el-dropdown-link{
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}

</style>
