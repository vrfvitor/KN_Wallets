import React, { useState } from "react";
import { logo } from "../../../assets/img";

import "./styles.scss"
import { IWallet } from "../../../types/wallet";
import { Button } from "@mui/material";
import TransactionModal, { ITransactionModal } from "../../Transaction";
import { toCents, toEuro } from "../../../common/util/money";
import { deposit, withdraw } from "../../../api";

function Wallet({wallet, updated}: { wallet: IWallet, updated: () => void }) {
  const [openWithdraw, setOpenWithdraw] = React.useState(false);
  const [openDeposit, setOpenDeposit] = React.useState(false);

  const [value, setValue] = useState<string>('')

  const handleClose = (out: boolean, confirmed: boolean = false) => {
    if (confirmed && +value > 0) {
      const amountCents = toCents(+value)
      const req = out ? withdraw(wallet.id, amountCents) : deposit(wallet.id, amountCents)
      req.then(() => updated())
    }
    setOpenWithdraw(false)
    setOpenDeposit(false)
    setValue('')
  };

  const withdrawModal: ITransactionModal = {
    title: 'Withdraw', btn: 'Confirm', out: true, open: openWithdraw, handleClose, wallet, value, setValue
  }
  const depositModal: ITransactionModal = {
    title: 'Deposit', btn: 'Confirm', out: false, open: openDeposit, handleClose, wallet, value, setValue
  }

  const handleOpen = (out: boolean = false) => {
    out ? setOpenWithdraw(true) : setOpenDeposit(true)
  };

  return (
      <>
        <TransactionModal {...withdrawModal}/>
        <TransactionModal {...depositModal}/>
        <div className="Wallet">
          <div className="General">
            <img src={logo} alt="Logo"/>
            <div className="DataGrid">
              <span>{wallet.owner.firstName} {wallet.owner.lastName}</span>
              <span className="span">{wallet.owner.email}</span>
              <span className="span">€ {toEuro(wallet.balanceCents)}</span>
            </div>

          </div>
          <div className="Actions">
            <span className="IdBox">{wallet.id}</span>
            <Button variant="contained" color="success" onClick={() => handleOpen()}>Deposit</Button>
            <Button variant="contained" color="success" onClick={() => handleOpen(true)}>Withdraw</Button>
          </div>
        </div>
      </>
  )

}

export default Wallet