import React, { useEffect, useState } from "react";
import { IWallet } from "../../types/wallet";
import Wallet from "./Wallet";
import { findAll } from "../../api";

function WalletList() {

  const [wallets, setWallets] = useState<IWallet[]>([])

  useEffect(() => {
    findAll("/wallets", setWallets)
  }, [])

  const update = () => {
    findAll("/wallets", setWallets)
  }

  return (
      <>
        {wallets.map((wallet, index) =>
            <Wallet key={index} wallet={wallet} updated={update}/>
        )}
      </>
  )
}

export default WalletList