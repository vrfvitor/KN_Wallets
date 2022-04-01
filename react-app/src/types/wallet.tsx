export interface IWallet {
  id: string
  owner: Person
  balanceCents: number
}

export interface Person {
  firstName: string
  lastName: string
  email?: string
}