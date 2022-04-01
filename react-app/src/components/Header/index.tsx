import React from "react"
import "./styles.scss"
import { logo, lLinkedin, lGithub } from "../../assets/img"

function Header() {
  const aLinkedIn = "https://linkedin.com/in/vrfvitor/"
  const aGitHub = "https://github.com/vrfvitor"

  return (
      <div className="Header">
        <div className="logoBox">
          <span className="logoText">K+N WALLETS</span>
          <img src={logo} alt="logo kn_wallets"/>
        </div>
        <div className="mediaBox">
          <div className="medias">
            <a href={aLinkedIn}>
              <img className="HeaderImage" title="LinkedIn" alt="Vitor Ferreira's LinkedIn" src={lLinkedin}/>
            </a>
            <a href={aGitHub}>
              <img className="HeaderImage" title="Github" alt="Vitor Ferreira's GitHub" src={lGithub}/>
            </a>
          </div>
          <span className="withLove">With 🫀 by Vitor Ferreira</span>
        </div>
      </div>
  )
}

export default Header