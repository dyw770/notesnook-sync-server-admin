import request from "@/api/request";

export function listUser(page: number, size: number) {
    return request.get("/admin/users?page=" + page + "&size=" + size);
}

export function deleteUser(id: string) {
    return request.delete("/admin/users", { params: { id: id }});
}

export function addUser(userName: string, password: string, email: string) {
    return request.post("/admin/users", { userName: userName, password: password, email: email });
}

export function lockUser(id: string, lockoutEnd: string) {
    return request.put("/admin/users/lock", { id: id, lockoutEnd: lockoutEnd });
}

export function userInfo() {
    return request.get("/user/info");
}
export function logout() {
    return request.get("/logout");
}