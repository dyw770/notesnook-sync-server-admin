import request from "@/api/request";

export function list(page: number, size: number) {
    return request.get("/admin/users?page=" + page + "&size=" + size);
}