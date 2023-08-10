import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  status: string | undefined;
  statusText: string | undefined;
  errorMessage: string | undefined;

  constructor(private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      this.status = params.has('status') ? params.get('status')!! : '';
      this.statusText = params.has('statusText') ? params.get('statusText')!! : '';
      this.errorMessage = params.has('errorMessage') ? params.get('errorMessage')!! : '';
    })
  }

  goBackToHomepage() {
    this.router.navigateByUrl("/competitions")
  }
}
