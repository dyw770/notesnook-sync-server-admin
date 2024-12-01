<template>
  <div>
    <el-table :data="userListData" style="width: 100%">
      <el-table-column prop="accessFailedCount" label="accessFailedCount" width="180" />
      <el-table-column prop="email" label="email" width="180" />
      <el-table-column prop="lockoutEnabled" label="lockoutEnabled" width="180" />
      <el-table-column prop="userName" label="userName" width="180" />
      <el-table-column prop="emailConfirmed" label="emailConfirmed" width="180" />
      <el-table-column prop="lockoutEnd" label="lockoutEnd" width="180" />
      <el-table-column prop="phoneNumber" label="phoneNumber" width="180" />
      <el-table-column prop="phoneNumberConfirmed" label="phoneNumberConfirmed" width="180" />
    </el-table>
    <el-pagination layout="prev, pager, next" :total="userTotal" />
  </div>
</template>

<script setup lang="ts">
import {list} from '@/api/notesnookUsers';
import {onMounted, ref} from "vue";

type NotesnookUser = {
    id: string,
    accessFailedCount: number,
    email: string,
    lockoutEnabled: boolean,
    userName: string,
    emailConfirmed: boolean,
    lockoutEnd: number,
    phoneNumber: string,
    phoneNumberConfirmed: string
  }

  const userTotal = ref(0)

  const userListData = ref<Array<NotesnookUser>>([])

  const userList = (page: number = 0, size: number = 10) => {
    list(page, size)
        .then((resp: any) => {
          console.log(resp);
          userTotal.value = resp.data.data.page.totalElements;
          userListData.value = resp.data.data.content;
        })
  }

  onMounted(() => {
    userList()
  })
</script>

<style scoped>

</style>