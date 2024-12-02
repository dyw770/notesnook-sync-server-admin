<template>
  <div class="home">
    <el-button class="logout-btn" type="danger" plain :icon="Right" @click="handleLogout">Logout</el-button>

    <div class="home-card">
      <el-card class="home-card-item">
        <template #header>
          <div>
            <span>User Count</span>
          </div>
        </template>
        {{ dashboardData.userCount }}
      </el-card>
      <el-card class="home-card-item">
        <template #header>
          <div>
            <span>Note Count</span>
          </div>
        </template>
        {{ dashboardData.noteCount }}
      </el-card>
      <el-card class="home-card-item">
        <template #header>
          <div>
            <span>Monograph Count</span>
          </div>
        </template>
        {{ dashboardData.monographCount }}
      </el-card>
      <el-card class="home-card-item">
        <template #header>
          <div>
            <span>Attachment Count</span>
          </div>
        </template>
        {{ dashboardData.attachmentCount }}
      </el-card>
    </div>

    <div class="home-data">
      <el-table height="calc(100% - 44px)" :data="userListData">
        <el-table-column
            type="index"
            width="50"
            label="#"/>
        <el-table-column
            prop="accessFailedCount"
            label="Access Failed"
            width="120"/>
        <el-table-column
            prop="email"
            label="email"
            width="200"/>
        <el-table-column
            prop="lockoutEnabled"
            label="Lockout Enabled"
            width="160"/>
        <el-table-column
            prop="userName"
            label="User Name"
            width="180"/>
        <el-table-column
            prop="emailConfirmed"
            label="Email Confirmed"
            width="180"/>
        <el-table-column
            prop="lockoutEnd"
            label="Lockout End"
            width="180">
          <template #default="scope">
            {{ dayjs(scope.row.lockoutEnd).format('YYYY-MM-DD HH:mm:ss') }}
          </template>
        </el-table-column>
        <el-table-column
            prop="phoneNumber"
            label="phoneNumber"
            width="180"/>
        <el-table-column
            prop="phoneNumberConfirmed"
            label="Phone Number Confirmed"
            width="180"/>
        <el-table-column
            fixed="right"
            label="Operations"
            min-width="120">
          <template #header>
            <el-button
                link
                type="primary"
                size="small"
                @click.prevent="showAddDialog">
              Add
            </el-button>
          </template>
          <template #default="scope">
            <el-button
                link
                type="danger"
                size="small"
                @click.prevent="handleDelete(scope.row.id)">
              Remove
            </el-button>
            <el-button
                link
                type="warning"
                size="small"
                @click.prevent="showLockDialog(scope.row)">
              Lock
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          class="home-data-page"
          layout="total, sizes, prev, pager, next"
          v-model:current-page="pageData.currentPage"
          v-model:page-size="pageData.pageSize"
          :page-sizes="[10, 50, 100, 500]"
          :total="pageData.total"/>

      <!-- lock dialog -->
      <el-dialog
          v-model="dialogShow.showLock"
          title="Lock User"
          width="500">
        <el-form :model="lockForm">
          <el-form-item
              label="ID"
              prop="id">
            <el-input
                v-model="lockForm.id"
                autocomplete="off"
                disabled/>
          </el-form-item>
          <el-form-item
              label="userName"
              prop="userName">
            <el-input
                v-model="lockForm.userName"
                autocomplete="off"
                disabled/>
          </el-form-item>
          <el-form-item
              label="Lockout End"
              prop="lockoutEnd">
            <el-date-picker
                v-model="lockForm.lockoutEnd"
                type="datetime"
                placeholder="Pick a Date"
                format="YYYY-MM-DDTHH:mm:ss.SSSZ"
                value-format="YYYY-MM-DDTHH:mm:ss.SSSZ"/>
          </el-form-item>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogShow.showLock = false">Cancel</el-button>
            <el-button
                type="primary"
                @click="handleLock">
              Confirm
            </el-button>
          </div>
        </template>
      </el-dialog>

      <!-- add dialog -->
      <el-dialog
          v-model="dialogShow.showAdd"
          title="Add User"
          width="500">
        <el-form :model="addForm">
          <el-form-item
              label="Username"
              prop="userName">
            <el-input
                v-model="addForm.userName"
                autocomplete="off"/>
          </el-form-item>
          <el-form-item
              label="Password"
              prop="password">
            <el-input
                v-model="addForm.password"
                type="password"
                autocomplete="off"/>
          </el-form-item>
          <el-form-item
              label="Email"
              prop="email">
            <el-input
                v-model="addForm.email"
                type="text"
                autocomplete="off"/>
          </el-form-item>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogShow.showAdd = false">Cancel</el-button>
            <el-button
                type="primary"
                @click="handleAdd">
              Confirm
            </el-button>
          </div>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script setup lang="ts">
import {Right} from '@element-plus/icons-vue'
import {addUser, deleteUser, listUser, lockUser, logout} from '@/api/notesnookUsers'
import {getDashboard} from '@/api/dashboard'
import {onMounted, ref} from 'vue'
import {ElMessage} from 'element-plus'
import dayjs from 'dayjs'
import router from "@/router";

type NotesnookUser = {
  id: string
  accessFailedCount: number
  email: string
  lockoutEnabled: boolean
  userName: string
  emailConfirmed: boolean
  lockoutEnd: string
  phoneNumber: string
  phoneNumberConfirmed: string
}

type Pagination = {
  currentPage: number
  pageSize: number
  total: number
}

type Dashboard = {
  noteCount: number
  userCount: number
  monographCount: number
  attachmentCount: number
}

const pageData = ref<Pagination>({
  currentPage: 1,
  pageSize: 10,
  total: 0
})

const dashboardData = ref<Dashboard>({
  noteCount: 0,
  userCount: 0,
  monographCount: 0,
  attachmentCount: 0
})

const lockFormInitValue = {
  id: '',
  userName: '',
  lockoutEnd: ''
}

const lockForm = ref(JSON.parse(JSON.stringify(lockFormInitValue)))

const initLockForm = () => {
  lockForm.value = JSON.parse(JSON.stringify(lockFormInitValue))
}

const addFormInitValue = {
  userName: '',
  password: '',
  email: ''
}

const addForm = ref(JSON.parse(JSON.stringify(addFormInitValue)))

const initAddForm = () => {
  addForm.value = JSON.parse(JSON.stringify(addFormInitValue))
}

const userListData = ref<Array<NotesnookUser>>([])

const userList = (page: number = 0, size: number = 10) => {
  listUser(page, size).then((resp: any) => {
    pageData.value.total = resp.data.data.page.totalElements
    userListData.value = resp.data.data.content
  })
}

const refresh = () => {
  userList(pageData.value.currentPage - 1, pageData.value.pageSize)
}

const dialogShow = ref({
  showLock: false,
  showAdd: false
})

const handleDelete = (id: string) => {
  deleteUser(id).then((res) => {
    ElMessage.success(res.data.msg)
    refresh()
  })
}

const showLockDialog = (user: NotesnookUser) => {
  lockForm.value.id = user.id
  lockForm.value.userName = user.userName
  lockForm.value.lockoutEnd = user.lockoutEnd
  dialogShow.value.showLock = true
}

const handleLock = () => {
  lockUser(lockForm.value.id, lockForm.value.lockoutEnd).then((res) => {
    ElMessage.success(res.data.msg)
    dialogShow.value.showLock = false
    initLockForm()
    refresh()
  })
}

const showAddDialog = () => {
  initAddForm()
  dialogShow.value.showAdd = true
}

const handleAdd = () => {
  addUser(
      addForm.value.userName,
      addForm.value.password,
      addForm.value.email
  ).then((res) => {
    ElMessage.success(res.data.msg)
    dialogShow.value.showAdd = false
    initAddForm()
    refresh()
  })
}

const loadDashboard = () => {
  getDashboard().then(resp => {
    dashboardData.value = resp.data.data
  })
}
const handleLogout = () => {
  logout().then((res) => {
    ElMessage.success(res.data.msg)
    router.push('/login')
  })
}

onMounted(() => {
  loadDashboard()
  userList(pageData.value.currentPage - 1, pageData.value.pageSize)
})
</script>

<style scoped>
.home {
  height: 100vh;
}

.home-card {
  display: flex;
  margin-bottom: 8px;
  gap: 10px;
  padding: 16px;
}

.home-card-item {
  flex: 1;
  flex-shrink: 0;
}

.home-data {
  padding: 10px 20px;
  height: calc(100vh - 182px);
}

.home-data-page {
  margin-top: 12px;
}

.logout-btn {
  position: fixed;
  right: 10px;
  bottom: 10px;
  z-index: 9;
}
</style>
