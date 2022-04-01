import React, { useState } from "react";
import { Box, Button, Modal } from "@mui/material";
import "./styles.scss"
import { IWallet } from "../../types/wallet";
import { toEuro } from "../../common/util/money";

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '1px solid #000',
  boxShadow: 24,
  p: 4,
  borderRadius: '8px'
};

export interface ITransactionModal {
  title: string
  btn: string
  out: boolean
  wallet: IWallet
  open: boolean
  handleClose: (out: boolean, s?: boolean) => void
  value: string
  setValue: (e: string) => void
}

export default function TransactionModal(props: ITransactionModal) {
  const fullName = `${props.wallet?.owner?.firstName} ${props.wallet?.owner?.lastName}`
  const amount = "amount";

  return (
      <Modal
          open={props.open}
          onClose={() => props.handleClose(props.out)}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <h2>{props.title}</h2>
          <div className="GridBody">
            <h3>{props.out ? 'From' : 'To'}:</h3>
            <span>{fullName}</span>
            <span className="AccountId">{props.wallet?.id}</span>
            <h3>Balance:</h3>
            <span className="Balance">€ {toEuro(props.wallet?.balanceCents)}</span>
            <span className="EuroSym">€</span>
            <input placeholder={amount} type="number" min="0" value={props.value} onChange={(e) => props.setValue(e.target.value)}/>
            <Button variant="contained" color="success" className="Btn"
                    onClick={() => props.handleClose(props.out, true)}>{props.btn}</Button>
          </div>
        </Box>
      </Modal>
  )

}