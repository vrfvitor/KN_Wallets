export function toEuro(amountCents: number) {
  return +(amountCents / 100).toFixed(2)
}

export function toCents(amount: number) {
  return +(amount * 100).toFixed(2)
}