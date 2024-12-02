import request from "@/api/request";

export function getDashboard() {
    return request.get("/admin/notes/dashboard");
}