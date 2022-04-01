import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080/api"
})

export const findAll = async (url: string, setData: any) => {
  const res = await api.get(url);
  setData(res.data)
}

export const deposit = async (id: string, amountCents: number) => {
  const res = await api.post(`/wallets/${id}/deposit`, {amountCents});
  return res.status
}

export const withdraw = async (id: string, amountCents: number) => {
  const res = await api.post(`/wallets/${id}/withdraw`, {amountCents});
  return res.status
}
